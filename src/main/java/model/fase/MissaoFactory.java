package model.fase;

import model.Missao;
import model.Consumivel;
import model.Efeito;
import model.inimigo.CacadorDeRecompensa;
import model.inimigo.Bebado;
import model.inimigo.Pistoleiro;
import model.inimigo.Bandido;
import model.inimigo.boss.Xerife;
import model.inimigo.boss.PeGrande;

import java.util.ArrayList;
import java.util.List;

public class MissaoFactory {

    public static List<Missao> criarMissoesPadrao() {
        List<Missao> missoes = new ArrayList<>();

        Missao m1 = new Missao("Saloon de Valentine", "Um bar movimentado com um bêbado muito incoveniente.");
        m1.adicionarInimigo(new Bebado("Bêbado"));
        m1.adicionarItem(new Consumivel("Whisky", "Recupera 20 de vida", 1, Efeito.CURA, 20));

        Missao m2 = new Missao("Assalto no Saloon", "Após sua briga com o bêbado, um bandido tenta te assaltar");
        m2.adicionarInimigo(new Bandido("Bandido"));
        m2.adicionarItem(new Consumivel("Gim", "Recupera 40 de vida", 1, Efeito.CURA, 20));

        Missao m3 = new Missao("Confronto na cidade", "Você se depara com um pistoleiro que te desafia para um duelo no meio de Valentine");
        m3.adicionarInimigo(new Pistoleiro("Pistoleiro sombrio"));
        m3.adicionarItem(new Consumivel("Dinamite", "Aumenta ataque", 1, Efeito.AUMENTO_ATAQUE, 10));

        Missao m4 = new Missao("Caçado", "Um caçador de recompensas atravessou o país para te encontrar e não vai voltar sem seu prêmio");
        m4.adicionarInimigo(new CacadorDeRecompensa("RIP VAN WINKLE"));
        m4.adicionarItem(new Consumivel("Tabaco de Mascar", "Aumenta defesa", 1, Efeito.AUMENTO_DEFESA, 10));

        Missao m5 = new Missao("Acerto de contas", "Você entra em confronto com um xerife corrupto");
        m5.adicionarInimigo(new Xerife("Xerife Manson"));
        m5.adicionarItem(new Consumivel("Explosivo", "Aumenta ataque", 1, Efeito.AUMENTO_ATAQUE, 20));

        Missao m6 = new Missao("Fim do tormento", "Você se depara com um Pé Grande nas redondezas de Valentine");
        m6.adicionarInimigo(new PeGrande("Sasquach"));
        m6.adicionarItem(new Consumivel("Proteção divina", "Aumenta defesa", 1, Efeito.AUMENTO_DEFESA, 30));

        missoes.add(m1);
        missoes.add(m2);
        missoes.add(m3);
        missoes.add(m4);
        missoes.add(m5);
        missoes.add(m6);

        return missoes;
    }
}
