package sy.video.model;

public class UserModelProxy implements sy.video.model.UserModel {
  private String _endpoint = null;
  private sy.video.model.UserModel userModel = null;
  
  public UserModelProxy() {
    _initUserModelProxy();
  }
  
  public UserModelProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserModelProxy();
  }
  
  private void _initUserModelProxy() {
    try {
      userModel = (new sy.video.model.UserModelServiceLocator()).getUserModel();
      if (userModel != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userModel)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userModel != null)
      ((javax.xml.rpc.Stub)userModel)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sy.video.model.UserModel getUserModel() {
    if (userModel == null)
      _initUserModelProxy();
    return userModel;
  }
  
  public sy.video.valueobj.User getUser(int userId) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.getUser(userId);
  }
  
  public sy.video.valueobj.User[] getUserByType(java.lang.String userType, int from, int pagesize) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.getUserByType(userType, from, pagesize);
  }
  
  public sy.video.valueobj.User authenticateUser(java.lang.String email, java.lang.String password) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.authenticateUser(email, password);
  }
  
  public sy.video.valueobj.User[] getUsers(int from, int pagesize) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.getUsers(from, pagesize);
  }
  
  public void saveUser(sy.video.valueobj.User u) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    userModel.saveUser(u);
  }
  
  public int deletUser(int userId) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.deletUser(userId);
  }
  
  public int addUser(sy.video.valueobj.User u) throws java.rmi.RemoteException{
    if (userModel == null)
      _initUserModelProxy();
    return userModel.addUser(u);
  }
  
  
}