/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrenZ.util;

/**
 *
 * @author himan
 */
public class OwnerProfile {

    public static String getOwnerName() {
        return OwnerName;
    }

    public static void setOwnerName(String OwnerName) {
        OwnerProfile.OwnerName = OwnerName;
    }

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        OwnerProfile.companyName = companyName;
    }

    public static String getCompanyId() {
        return companyId;
    }

    public static void setCompanyId(String companyId) {
        OwnerProfile.companyId = companyId;
    }
    private static String OwnerName;
    private static String companyName;
    private static String companyId;
    
    
}
