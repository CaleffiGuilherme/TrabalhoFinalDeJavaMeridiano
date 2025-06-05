package model.player;

public abstract class Personagem {
        protected String nome;
        protected int vidaAtual;
        protected int vidaMaxima;
        protected int ataque;
        protected int defesa;
        protected int esquiva;
        protected int precisao;
        protected String arma;

        public Personagem(String nome, int vidaAtual, int vidaMaxima, int ataque, int defesa, int esquiva, int precisao, String arma) {
                this.nome = nome;
                this.vidaAtual = vidaAtual;
                this.vidaMaxima = vidaMaxima;
                this.ataque = ataque;
                this.defesa = defesa;
                this.esquiva = esquiva;
                this.precisao = precisao;
                this.arma = arma;

        }

        public void atacar(Personagem inimigo){
                int dano = this.ataque - inimigo.getDefesa();
                if (dano < 0) dano = 0;
                inimigo.receberDano(dano);
                System.out.println(this.nome + " atacou " + inimigo.getNome() + " inferindo " + dano + " de dano.");
        }

        public void receberDano(int dano) {
                this.vidaAtual -= dano;
                if(this.vidaAtual <= 0) vidaAtual = 0;
        }

        public String getNome() { return nome; }
        public int getDefesa() { return defesa; }
}