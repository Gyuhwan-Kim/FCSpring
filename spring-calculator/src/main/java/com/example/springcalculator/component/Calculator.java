package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calculator {
    private final ICaculator iCaculator;

    public int sum(int x, int y){
        this.iCaculator.init();
        return this.iCaculator.sum(x, y);
    }

    public int sub(int x, int y){
        this.iCaculator.init();
        return this.iCaculator.sub(x, y);
    }
}
