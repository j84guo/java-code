class PropagatedException {

    void example() throws Exception{
        throw new Exception("This exception will always be thrown.");
    }
    
    public static void main(String[] args){
        PropagatedException p = new PropagatedException();
        
        try{
            p.example();
        }
        catch(Exception e){
            System.err.println( e.getClass().getSimpleName() + ": " + e.getMessage() );
        }
    }
}
