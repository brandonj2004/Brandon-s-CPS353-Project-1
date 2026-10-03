package apiproject;

public interface UserJobRequest {
    JobSource getSource();
    JobDestination getDestination();
    
    default String getDelimiters() {
        return ",";
    }
}