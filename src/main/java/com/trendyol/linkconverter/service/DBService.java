package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.repository.DbRepository;
import com.trendyol.linkconverter.repository.model.URLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface DBService {
    void saveRequest(URLRequest urlRequest);
}
