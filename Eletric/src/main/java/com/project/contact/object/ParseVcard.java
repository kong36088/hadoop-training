package com.project.contact.object;


import net.sourceforge.cardme.engine.VCardEngine;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;

import java.io.File;
import java.io.IOException;

/**
 * Created by jwl on 2016/5/2.
 */
public class ParseVcard {
    private VCardEngine vcardEngine;
    public ParseVcard(){
        vcardEngine=new VCardEngine();
    }
    public void setCompatibilityMode(CompatibilityMode compatMode) {
        vcardEngine.setCompatibilityMode(compatMode);
    }
    public void parseVCard(File file) {
        VCardEngine vcardEngine = new VCardEngine();
        VCard vcard=null;
        try {
           vcard = vcardEngine.parse(file);
        } catch (IOException ioe) {
            System.err.println("Could not read vcard file");
            ioe.printStackTrace();
        } catch (VCardParseException e) {
            e.printStackTrace();
        }
        System.out.println(vcard);
    }
}
