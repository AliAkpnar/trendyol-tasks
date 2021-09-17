package com.trendyol.linkconverter.service;

import com.trendyol.linkconverter.repository.DbRepository;
import com.trendyol.linkconverter.repository.model.URLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dbService")
public class DbServiceImpl implements DBService {

    @Autowired
    private DbRepository dbRepository;

    @Override
    public void saveRequest(URLRequest urlRequest) {
        dbRepository.save(urlRequest);
    }
}
