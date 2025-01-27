package br.edu.iftm.tspi.pmvc.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    
    private String usuario;
    private String senha;

}
