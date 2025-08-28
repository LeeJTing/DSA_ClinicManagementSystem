package control;

/**
 *
 * @author User
 */
public interface CRUD {
    
    // add new intrance
    public abstract void createNewInstance();
    
    // display all record or specific record
    public abstract void readInstance();
    
    // update existing record
    public abstract void updateInstance();
    
    // delete record
    public abstract void deleteInstance();
    
}
