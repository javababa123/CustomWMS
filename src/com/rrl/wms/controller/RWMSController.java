package com.rrl.wms.controller;

import java.util.List;

import javax.validation.Valid;

import com.rrl.wms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rrl.wms.dao.ArticleDao;
import com.rrl.wms.entity.Article;
import com.rrl.wms.entity.DC;
import com.rrl.wms.entity.Location;
import com.rrl.wms.entity.OrderLocationInv;
import com.rrl.wms.entity.Shipment;
import com.rrl.wms.entity.Tenant;
import com.rrl.wms.facade.CreateShipmentFacade;
import com.rrl.wms.util.exception.RWMSException;

@RestController
@RequestMapping("rwms")
public class RWMSController {
	
    @Autowired
    private ArticleDao articleDAO;

    @Autowired
    private ArticleService articleService;
	
	@Autowired
    public ShipmentService shipmentService;
    
    @Autowired
    public CreateShipmentFacade shipmentUpdate;
    
    @Autowired
    OrderLocationInvService orderLocationInvService;
    
    @Autowired
    TenantService tenantService;
    
    @Autowired
    LocationService locationService;
    
    @Autowired
    DCService dcService;

    @Autowired
    private SortingService sortingService;
	
	@Autowired
    TibcoJMSClient tibcoJMSClient;

    List<OrderLocationInv> listOrderLocationInv = null;

    // ****************** Tenant mappings ************
    @PostMapping("/tenant/create")
    public Tenant createTenant(@Valid @RequestBody Tenant tenant){
        return tenantService.createTenant(tenant);
    }

    @GetMapping("/tenant/find/{id}")
    public Tenant findTenant(@PathVariable(value = "id")  String tenantId){
        return tenantService.findTenant(tenantId);
    }

    @GetMapping("/tenant/getAll")
    public List<Tenant> getAllTenants(){
        return tenantService.getAllTenants();
    }

    // ****************** Location mappings ************
    @PostMapping("/location/create")
    public Location createLocation(@Valid @RequestBody Location location){
        return locationService.createLocation(location);
    }

    @GetMapping("/location/getAll")
    public List<Location> getAllLocations(){
        return locationService.findall();
    }

    @GetMapping("/location/find/{id}")
    public Location findLocation(@PathVariable(value = "id")  String locationId){
        return locationService.findByLocationId(locationId);
    }

    // ****************** DC mappings ************
    @PostMapping("/dc/create")
    public DC createDC(@Valid @RequestBody DC dc){
        return dcService.createDC(dc);
    }

    @GetMapping("/dc/getAll")
    public List<DC> getAllDCs(){
        return dcService.findall();
    }

    @GetMapping("/dc/find/{id}")
    public DC findDC(@PathVariable(value = "id")  String dcCode){
        return dcService.findByDcCode(dcCode);
    }

    // ****************** Article mappings ************

    @GetMapping("/article/getAll")
    public List<Article> getAllArticles(){
        return articleDAO.findAll();
    }

    @PostMapping("/article/create")
    public Article createArticle(@Valid @RequestBody Article article){
        return articleService.createArticle(article);
    }

    @GetMapping("/article/find/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") String id){
        Article article = articleDAO.findOne(id);
        if (article==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(article);
    }

    @PutMapping("/article/update")
    @Transactional(rollbackFor = RWMSException.class)
    public ResponseEntity<Article> updateArticle(@RequestBody Article article) throws RWMSException{
        Article updatedArticle = articleService.updateArticle(article);

        return ResponseEntity.ok().body(updatedArticle);
    }

    // ****************** Shipment mappings ************

    @PostMapping("/shipments/create")
    @Transactional(rollbackFor = RWMSException.class)
    public Shipment createShipment(@Valid @RequestBody Shipment shipment) throws Exception{
//        return shipmentService.createShipment(shipment);
    	
    	return shipmentUpdate.createShipmentAndUpdateLocation(shipment);
    }

    @PostMapping("/shipments/find/{id}")
    public Shipment findShipment(@PathVariable(value = "id") String shipmentNo){
       return shipmentService.findByShipmentNo(shipmentNo);
    }

    // ****************** OrderLocationInventory mappings ************
    
    @PostMapping("/orderlocationInv/create")
    public OrderLocationInv recordInv(@Valid @RequestBody OrderLocationInv orderLocationInv){
        return orderLocationInvService.recordInv(orderLocationInv);
    }

    @PostMapping("adjustInventory")
    public OrderLocationInv adjustInventory(@Valid @RequestBody OrderLocationInv orderLocationInv) throws Exception{
        return orderLocationInvService.adjustInventory(orderLocationInv);
    }

    @PostMapping("/inventory/find")
    public OrderLocationInv findRecord(@Valid @RequestBody OrderLocationInv orderLocationInv) throws Exception{
        return orderLocationInvService.findRecord(orderLocationInv);
    }

    // ****************** Sorting mappings ************

    @GetMapping("/sorting/itemscan/tenant={tenant}&item={item}&dc={dc}")
    public List<OrderLocationInv> itemScan(@PathVariable(value = "tenant") String sTenant, @PathVariable(value = "item") String sItem,@PathVariable(value = "dc") String sDC) throws Exception{

        listOrderLocationInv = sortingService.scanItem(sTenant,sItem,sDC);

        return listOrderLocationInv;
    }

    @GetMapping("/sorting/locationscan/tenant={tenant}&item={item}&dc={dc}&shipment={shipment}&location={location}")
    public List<OrderLocationInv> locationScan(@PathVariable(value = "tenant") String sTenant, @PathVariable(value = "item") String sItem,@PathVariable(value = "dc") String sDC,@PathVariable(value = "shipment") String sShipment,@PathVariable(value = "location") String sLocation) throws Exception{

        listOrderLocationInv = sortingService.scanLocation(sTenant,sItem,sDC, sShipment,sLocation);

        return listOrderLocationInv;
    }
	
	// ****************** Tibco Queue Testing ************

    @GetMapping("/tibcoQ/sendMessage/message={message}")
    public String send(@PathVariable(value = "message") String msg){
        tibcoJMSClient.send(msg);
        return "Done";
    }

    @GetMapping(value="/tibcoQ/receiveMessage")
    public String receive(){
        return tibcoJMSClient.receive();
    }



}
