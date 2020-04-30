package com.kpdoggie.instrumentation;


import org.springframework.stereotype.Service;

@Service
public class RandomService {

    public String randomLogic() {
        throw new IllegalArgumentException();
    }

}
