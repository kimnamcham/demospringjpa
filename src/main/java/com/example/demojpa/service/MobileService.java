package com.example.demojpa.service;

import com.example.demojpa.dto.MobileDTO;
import com.example.demojpa.entity.Cart;
import com.example.demojpa.entity.Mobile;
import com.example.demojpa.repository.CartRepository;
import com.example.demojpa.repository.CustomRepository;
import com.example.demojpa.repository.MobileRepository;
import com.example.demojpa.request.CreateMobileRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MobileService {

    private MobileRepository mobileRepository;

    private CustomRepository customRepository;
    private CartRepository cartRepository;

    @Autowired
    public MobileService(MobileRepository mobileRepository,
                         CustomRepository customRepository,
                         CartRepository cartRepository) {
        this.mobileRepository = mobileRepository;
        this.customRepository = customRepository;
        this.cartRepository = cartRepository;
    }

    public List<Mobile> getListMobile() {
        return mobileRepository.findAll();
    }

    public List<Mobile> getListMobile(Sort.Direction direction, String... fieldSort) {
        return mobileRepository.findAll(Sort.by(direction, fieldSort));
    }

    @Transactional
    public MobileDTO create(CreateMobileRequest request) {
        Cart cart = Cart.builder()
                .build();
        Mobile mobile = Mobile.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .build();

        Mobile mobile1 = Mobile.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .build();
//        mobile = mobileRepository.saveAndFlush(mobile);
        mobile1.setCart(cart);
        mobile.setCart(cart);
        cart.setMobiles(new HashSet<>(Arrays.asList(mobile, mobile1)));
        cartRepository.saveAndFlush(cart);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(mobile, MobileDTO.class);
    }

    public List<Mobile> filterListMobile(Integer type, Integer active) {
        return customRepository.filterMobileByTypeAndActive(type, active);
    }

    public Page<Mobile> filterMobileList(Integer typeFrom, Integer typeTo,
                                         Integer active,
                                         Date dateFrom, Date dateTo,
                                         String name,
                                         Integer start,
                                         Integer limit) {
        Specification<Mobile> specification = CustomRepository.buildFilterSpecification(typeFrom, typeTo, active, dateFrom, dateTo, name);
        return mobileRepository.findAll(specification, PageRequest.of(start, limit));
    }

    public Mobile findById(Long id) {
        return mobileRepository.findById(id).orElseThrow(() -> new RuntimeException("Mobile not found"));
    }

    public void deleteById(Long id) {
        Optional<Mobile> mobile = mobileRepository.findById(id);
        if (mobile.isPresent()) {
            Cart cart = mobile.get().getCart();
            if (cart != null) {
                cart.getMobiles().removeIf(mobile1 -> mobile1.getId().equals(id));
            }
        }
//        cartRepository.deleteById(id);
    }
}
