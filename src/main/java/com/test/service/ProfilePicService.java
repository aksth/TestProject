package com.test.service;

import com.test.entity.ProfilePic;
import com.test.repository.ProfilePicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by intern1 on 5/7/2017.
 */
@Service
@Transactional
public class ProfilePicService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ProfilePicRepository profilePicRepository;

    @Autowired
    FileService fileService;

    public List<ProfilePic> findAll(){
        return profilePicRepository.findAll();
    }

    public ProfilePic create(ProfilePic profilePic){
        System.out.println(profilePic.getId());
        System.out.println(profilePic.getAdmin());
        System.out.println(profilePic.getFilename());

        return profilePicRepository.save(profilePic);
    }

    public ProfilePic findByAdminId(Long id){
        return profilePicRepository.findByAdminId(id);
    }

    public void deleteProfilePicByAdminId(Long id){
        try {
            ProfilePic pp = findByAdminId(id);

            if(pp != null){
                //delete profile pic file first
                String filename = pp.getFilename();
                String pathparam = "akash/ProfilePictureUploads/Admins/";
                fileService.deleteFile(filename, pathparam);

                //then remove from db
                em.remove(pp);
            }
        }catch(Exception e){}
    }
}
