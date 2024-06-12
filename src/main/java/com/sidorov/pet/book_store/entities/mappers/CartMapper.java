package com.sidorov.pet.book_store.entities.mappers;

import com.sidorov.pet.book_store.entities.Cart;
import com.sidorov.pet.book_store.entities.dto.CartDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CartMapper {
    ModelMapper modelMapper;

    public CartDTO toDTO(Cart cart) {
        return modelMapper.map(cart, CartDTO.class);
    }

    public Cart toEntity(CartDTO cartDTO) {
        return modelMapper.map(cartDTO, Cart.class);
    }
}
