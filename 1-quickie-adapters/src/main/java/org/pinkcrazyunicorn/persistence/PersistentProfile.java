package org.pinkcrazyunicorn.persistence;

import java.util.*;

public interface PersistentProfile {
    String getName();
    Set<String> getAvailable();
    Map<String, String> getOpinions();
    void setName(String name);
    void setAvailable(Set<String> available);
    void setOpinions(Map<String, String> opinions);
}
