package com.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.ws.rs.Encoded;

@Entity
@Table(name="Device_Details")
public class Device {
	@Id
	@Column(name="Device_id")
	private String deviceid;
	
	@Column(name="Device_type")
	private String deviceType;
	
	
	public Device()
	{
		
	}
	
	public Device(String id,String type) {
		this.deviceid=id;
		this.deviceType=type;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "device id :"+deviceid+"    Device Type:"+deviceType;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	

}
