package com.stee.datamanagement;

import com.stee.sel.alarm.DeviceAlarmsEntity;
import com.stee.sel.constant.AlarmCategory;
import com.stee.sel.constant.OperationState;
import com.stee.sel.constant.SeverityLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatamanagementApplicationTests {

	@Resource
	private RestTemplate restTemplate;
	@Test
	public void getDevice(){
		String url="http://192.168.0.120:9023/FailureEvent/save";
//		RestTemplate restTemplate = new RestTemplate();
		ArrayList<DeviceAlarmsEntity> alarmList = new ArrayList<DeviceAlarmsEntity>();
		DeviceAlarmsEntity dae = new DeviceAlarmsEntity();
		dae.setSource("luminaire_003");
		dae.setCreateTime(new Timestamp(System.currentTimeMillis()));
		dae.setSeverityLevel(SeverityLevel.Informational.getIndex());
		dae.setCategory(AlarmCategory.Lamp.getCategory());
		dae.setDescription(OperationState.LampFailure.getState());
		alarmList.add(dae);
//		restTemplate.postForLocation(url, alarmList);
		String response=restTemplate.postForObject(url, alarmList, String.class);
		System.out.println(response);
	}

}
