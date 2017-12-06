package spittr.data;

import spittr.Spitter;

// person repository
public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
