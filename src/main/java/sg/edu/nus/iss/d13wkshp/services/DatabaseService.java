package sg.edu.nus.iss.d13wkshp.services;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.io.*;
import java.util.*;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.d13wkshp.models.Contact;

@Service
public class DatabaseService {
    
    private File dataDir = new File("some directory");

    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    //  for mac > /Users/<username>/data
    //  alternate for mac > /home/data
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

	public boolean save(final Contact contact) {
        File f = new File(this.dataDir, contact.getId());
        try (OutputStream out = new FileOutputStream(f)) {
            PrintWriter pw = new PrintWriter(out);
            pw.println(contact.getId());
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getPhone());
            pw.flush();

        } catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            // ex.printStackTrace();
            return false;
        }

        return false;
	}

	public Contact read(String fileId) {
        
        try {
            // File f = new File(this.dataDir, fileId);
            // Scanner myReader = new Scanner(f);
            // while (myReader.hasNextLine()) {
                // System.out.println(myReader.nextLine()); 

            //    }
            // myReader.close();

            Contact contact = new Contact();

            Path filePath = new File(this.dataDir, fileId).toPath();
            Charset charset = Charset.forName("utf-8");
            List<String> stringVal = Files.readAllLines(filePath, charset);
            
            contact.setName(stringVal.get(1));
            contact.setEmail(stringVal.get(2));
            contact.setPhone(stringVal.get(3));

            return contact;

        } catch(IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
	}
}
