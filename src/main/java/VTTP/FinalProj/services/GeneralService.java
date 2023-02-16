package VTTP.FinalProj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VTTP.FinalProj.repositories.ApiRepository;
import VTTP.FinalProj.repositories.DatabaseRepository;

@Service
public class GeneralService {
    
    @Autowired
    DatabaseRepository dbRepo;
    @Autowired
    ApiRepository aRepo;

    public boolean checkLogin(String email, String password){
        return dbRepo.checkLoginSQL(email, password);
    } 

    public boolean register(String email, String password){
        return dbRepo.registerSQL(email, password);
    } 

    public String search(String keyword){
        return aRepo.searchRestaurantByKeyword(keyword);
    }
}
