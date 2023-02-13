package org.kgusta;

import org.kgusta.repository.UserRepository;
import org.kgusta.util.InsertData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserRepository userRepository = new UserRepository();
        System.out.println(userRepository.findAll().toString());
    }


}
