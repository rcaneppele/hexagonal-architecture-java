package br.com.caelum.cursos.adapters.database.jpa.entity;

import br.com.caelum.cursos.domain.core.estudante.Telefone;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneVo {

    @Column(name = "telefone_ddd")
    private String ddd;
    @Column(name = "telefone_numero")
    private String numero;
    @Column(name = "telefone_whatsapp")
    private Boolean whatsapp;

    public TelefoneVo(Telefone telefone) {
        this(telefone.ddd(), telefone.numero(), telefone.whatsapp());
    }

}
