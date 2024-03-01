package com.joy187.minepal.config;

import java.util.List;

public class GunStack {
    private String gunName;
    private int gunNumber; //0: handgun, 1: rifle , 2: uzi , 3: shotgun, 4: sniper 5: rpg 6: magmum 7: machine gun

    public GunStack(String gunName, int gunNumber) {
        this.gunName = gunName;
        this.gunNumber = gunNumber;
    }

    public String getGunName() {
        return gunName;
    }

    public int getGunNumber() {
        return gunNumber;
    }

}
