package com.cydeo.tests.day09_post_put_delete;

import com.cydeo.pojo.Spartan;
import com.cydeo.utils.SpartanRestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanPostThenGET extends SpartanPostTest{
    //newSpartan object with random values
    Spartan newSpartan = SpartanRestUtils.getNewSpartan();

    @DisplayName("POST /spartan -> GET with id and compare")
    @Test
    public void postNewSpartanThenGETTest() {
        System.out.println("newSpartan = " + newSpartan);

    }
}
