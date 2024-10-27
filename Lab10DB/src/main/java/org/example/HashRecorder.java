package org.example;


import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class HashRecorder {
    private static final String host = "localhost";
    private static final int port = 6379;

    private final Jedis jedis;

    public HashRecorder() {
        jedis = new Jedis(host, port);
    }

    public void createPassport(String id, String dateOfBirth, String placeOfBirth, String sex) {
        Map<String, String> passport = new HashMap<>();
        passport.put("id", id);
        passport.put("dateOfBirth", dateOfBirth);
        passport.put("placeOfBirth", placeOfBirth);
        passport.put("sex", sex);
        jedis.hset("passport:" + id, passport);
    }

    public void createSGC(String id, String avScore, String honor) {
        Map<String, String> sgcData = new HashMap<>();
        sgcData.put("id", id);
        sgcData.put("avScore", avScore);
        sgcData.put("honor", honor);
        jedis.hset("SGC:" + id, sgcData);
    }

    public void createSpecialty(String code, String name, String curriculum, String department) {
        Map<String, String> specialtyData = new HashMap<>();
        specialtyData.put("code", code);
        specialtyData.put("name", name);
        specialtyData.put("curriculum", curriculum);
        specialtyData.put("department", department);
        jedis.hset("Specialty:" + code, specialtyData);
    }

    public void createApplicant(String name, String surname, String passportId, String ecId, String sgcId, String specialtyCode) {
        Map<String, String> applicant = new HashMap<>();
        applicant.put("name", name);
        applicant.put("surname", surname);
        applicant.put("passport", passportId);
        applicant.put("ec", ecId);
        applicant.put("sgc", sgcId);
        applicant.put("specialty", specialtyCode);
        jedis.hset("Applicant", applicant);
    }

    public Map<String, String> read(String key) {
        return jedis.hgetAll(key);
    }

    public void updatePassport(String id, String dateOfBirth, String placeOfBirth, String sex) {
        Map<String, String> passport = new HashMap<>();
        passport.put("dateOfBirth", dateOfBirth);
        passport.put("placeOfBirth", placeOfBirth);
        passport.put("sex", sex);
        jedis.hset("Passport:" + id, passport);
    }


    public void updateSpecialty(String code, String name, String curriculum, String department) {
        Map<String, String> specialty = new HashMap<>();
        specialty.put("name", name);
        specialty.put("curriculum", curriculum);
        specialty.put("department", department);
        jedis.hset("Specialty:" + code, specialty);
    }

    public void delete(String key) {
        jedis.del(key);
    }


}
