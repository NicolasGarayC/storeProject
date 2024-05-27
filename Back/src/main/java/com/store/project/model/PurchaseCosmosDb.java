package com.store.project.model;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Container(containerName = "rgstoredbcontainer")
public class PurchaseCosmosDb {

    @Id
    private String id;
    private int user;
    private double value;
    private Map<String, String> isbns;
    private String date;
    private String ip;

    public PurchaseCosmosDb() {
    }

    public PurchaseCosmosDb(String id, int user, double value, Map<String, String> isbns, String date, String ip) {
        this.id = id;
        this.user = user;
        this.value = value;
        this.isbns = isbns;
        this.date = date;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Map<String, String> getIsbns() {
        return isbns;
    }

    public void setIsbns(Map<String, String> isbns) {
        this.isbns = isbns;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
