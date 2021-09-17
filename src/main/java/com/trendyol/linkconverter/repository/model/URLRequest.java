package com.trendyol.linkconverter.repository.model;

import com.trendyol.linkconverter.repository.LinkType;

import javax.persistence.*;

@Entity
@Table(name = "URL_REQUEST")
public class URLRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "request")
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "urlType")
    private UrlType urlType; //deep or normal

    @Column(name = "linkType")
    private LinkType linkType; //search or product


    public URLRequest(String request, String response, UrlType urlType, LinkType linkType) {
        this.request = request;
        this.response = response;
        this.urlType = urlType;
        this.linkType = linkType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public UrlType getUrlType() {
        return urlType;
    }

    public void setUrlType(UrlType urlType) {
        this.urlType = urlType;
    }

    public LinkType getLinkType() {
        return linkType;
    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }
}
