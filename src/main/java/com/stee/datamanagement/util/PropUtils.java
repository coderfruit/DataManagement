package com.stee.datamanagement.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/* Copyright (C) 2016, ST Electronics Info-Comm Systems PTE. LTD
 * All rights reserved.
 *
 * This software is confidential and proprietary property of 
 * ST Electronics Info-Comm Systems PTE. LTD.
 * The user shall not disclose the contents of this software and shall
 * only use it in accordance with the terms and conditions stated in
 * the contract or license agreement with ST Electronics Info-Comm Systems PTE. LTD.
 *
 * Project Name : GZMMgt
 * File Name    : PropUtils.java
 * Author       : xiongxiaobo
 * Created      : 2016年10月25日 下午2:01:54
 *
 * <p> History : <br><br>
 * 读取配置类，加这个方法，是为了 读取远程配置时，方便扩展
 * SNo / CR PR_No / Modified by / Date Modified / Comments <br>
 * --------------------------------------------------------------------------------
 *  
 */

public class PropUtils extends PropertyPlaceholderConfigurer{
	
	private static Logger logger = LoggerFactory.getLogger("com.stee.datamanagement.util.PropUtils");
	private static Map staticPropertiesMap;
	private String[] remotes;
	
	public PropUtils(){
	}
	
	public String[] getRemotes() {
		return remotes;
	}
	public void setRemotes(String[] remotes) {
		this.remotes = remotes;
	}
	
	public static Object getContextProperty(String name){
		return staticPropertiesMap.get(name);
	}
	
	private void printRemoteFiles(){
		String arr$[] = remotes;
		int len$ = arr$.length;
		for(int i$ = 0 ; i$ < len$ ; i$++){
			String str = arr$[i$];
			logger.info(String.format("Init remote properties: [%s]", new Object[]{
					str
			}));
		}
	}
	
	private void closeStream(Closeable closeables[]){
		Closeable arr$[] = closeables;
		int len$ = arr$.length;
		for(int i$ = 0; i$ < len$; i$++){
			Closeable c = arr$[i$];
            if(c == null)
                continue;
            try{
                c.close();
            }catch(IOException e){
                logger.warn((new StringBuilder()).append("Exception in closing ").append(c).toString(), e);
            }
		}
	}
	
	public void readRemoteFile(String rurl,Properties pros){
		try{
			InputStream is = null;
			URL url = new URL(rurl);
			is = url.openStream();
			pros.load(is);
			closeStream(new Closeable[]{is});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory,
			Properties pros) throws BeansException {
		if(remotes != null && remotes.length > 0){
			printRemoteFiles();
			String arr$[] = remotes;
			int len$ = arr$.length;
			for(int i$ = 0; i$ < len$; i$ ++ ){
				String url = arr$[i$];
				readRemoteFile(url,pros);
			}
		}
		super.processProperties(beanFactory, pros);
		staticPropertiesMap = new HashMap();
		String keyStr;
		String value;
		for(Iterator i$ = pros.keySet().iterator(); i$.hasNext(); staticPropertiesMap.put(keyStr, value)){
			Object key = i$.next();
            keyStr = key.toString();
            value = pros.getProperty(keyStr);
		}	
	}
	
}
