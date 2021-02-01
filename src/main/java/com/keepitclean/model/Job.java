package com.keepitclean.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "JOB")
@Component
public class Job implements Serializable{

	private static final long serialVersionUID = -5875020908107657711L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Job_Id", nullable = false)
	private long jobId;
	
	@ElementCollection
	private Collection<String> jobNames;
	
	@Autowired
	@Embedded
	private Address jobAddress;
	
	@Autowired
	@OneToOne
	private Client client;
	
	public Job() {
		jobNames = new ArrayList<>();
	}
	
	public Job(Client client, Address jobAddress) {
		this.client = client;
		this.jobAddress = jobAddress;
		jobNames = new ArrayList<>();
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Collection<String> getJobNames() {
		return jobNames;
	}

	public void setJobNames(Collection<String> jobNames) {
		this.jobNames = jobNames;
	}

	public Address getJobAddress() { return jobAddress;}
	
	public void setJobAddress(Address jobAddress) { 
		this.jobAddress = jobAddress;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}
	
	
}
