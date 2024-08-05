package Gatio.models;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Comanda {
    @Id
    private ULID.Value id;
    private String numero;
    private boolean aberta;
}
