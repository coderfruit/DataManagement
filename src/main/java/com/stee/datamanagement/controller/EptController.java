package com.stee.datamanagement.controller;

import com.stee.datamanagement.entity.ElectricAlertInfo;
import com.stee.datamanagement.service.AlertToLFMService;
import com.stee.datamanagement.service.IElectricAlertService;
import com.stee.datamanagement.service.IUsageAlertService;
import com.stee.datamanagement.util.QueryBean;
import com.stee.sel.lfm.UsageAlertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class EptController {
    @Autowired
    private IUsageAlertService usageAlertService;

    @Autowired
    private IElectricAlertService electricAlertService;

    @Autowired
    private AlertToLFMService alertToLFMService;

    @RequestMapping(value="usage/get" , method= RequestMethod.GET)
    @ResponseBody
    public Page<UsageAlertInfo> getUsageAlertInfo(@RequestParam(value="id") String id,
                                                  @RequestParam(value="start",defaultValue="0")String start,
                                                  @RequestParam(value="end",defaultValue="100")String end,
                                                  @RequestParam(value="page",defaultValue="0")Integer page,
                                                  @RequestParam(value="size",defaultValue="10")Integer size){
        System.out.println("page:"+page+"size:"+size);
        int starti=0;
        int endi =100;
        try {
            starti = Integer.parseInt(start);
            endi = Integer.parseInt(end);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("error");
        }
        return usageAlertService.getUsageAlertInfo(id,starti,endi,page, size);

    }

    @RequestMapping(value="electric/testget" , method=RequestMethod.GET)
    @ResponseBody
    public List<ElectricAlertInfo> testGetAll(@RequestParam(value="id") String id){
        return electricAlertService.getTestAll();
    }

    @RequestMapping(value="test/testget" , method=RequestMethod.GET)
    @ResponseBody
    public void testGetAll(){

        List<UsageAlertInfo> list = new ArrayList<UsageAlertInfo>();
        for(int i = 0;i<5;i++){
            UsageAlertInfo uai = new UsageAlertInfo();
            uai.setId("lamp"+i);
            uai.setModelId("model"+i);
            uai.setSeverityLevel(i);
            uai.setUsage(3000.0);
            uai.setUpdateTime(new Date());
            uai.setUsePercentage(new Random().nextInt(100));
            uai.setAlertMsg("test failrue");
            list.add(uai);
        }
//		alertToLFMService.saveAndAlertElectric(null);
        alertToLFMService.saveAndAlertUsage(list);
//		RestTemplate rt = new RestTemplate();
//		String s = rt.getForObject("http://192.168.0.197:9110/dashboard/energy/getallbydaily?month=1", String.class);
//		System.out.println(s);
    }

    @RequestMapping(value="electric/get" , method=RequestMethod.POST)
    @ResponseBody
    public Page<ElectricAlertInfo> getElectricAlertInfo(@RequestBody(required=false) QueryBean term,
                                                        @RequestParam(value="page",defaultValue="0")Integer page,
                                                        @RequestParam(value="size",defaultValue="10")Integer size){
        System.out.println("EPTManagementController getElectricAlertInfo START");
        System.out.println("EPTManagementController getElectricAlertInfo term--"+term.toString());
        return electricAlertService.getElectricAlertInfo(term, page, size);

    }
}
