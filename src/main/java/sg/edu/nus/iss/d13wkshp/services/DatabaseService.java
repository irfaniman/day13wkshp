package sg.edu.nus.iss.d13wkshp.services;

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
		return false;
	}

	public Contact read(String fileId) {
        return null;
	}
}
