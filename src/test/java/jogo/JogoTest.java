package jogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class JogoTest {

    private Jogador jogadorMock;
    private Dado dadinho1Mock;
    private Dado dadinho2Mock;
    private Jogo jogoMock;

    @BeforeEach
    void inicializaClasses() {
        jogadorMock = mock(Jogador.class);
        dadinho1Mock = mock(Dado.class);
        dadinho2Mock = mock(Dado.class);
        jogoMock = new Jogo(jogador(jogadorMock, dadinho1Mock, dadinho2Mock));
    }

    @Test
    void venceNoPrimeiroCom7() {
        when(jogadorMock.lancar(any(), any())).thenReturn(7);
        Jogo jogoMock = new Jogo(jogadorMock, dadinho1Mock, dadinho2Mock);
        assertTrue(jogoMock.jogo());
    }
	
    @Test
    void venceNoPrimeiroCom11() {
        when(jogadorMock.lancar(any(), any())).thenReturn(11);
        Jogo jogoMock = new Jogo(jogadorMock, dadinho1Mock, dadinho2Mock);
        assertTrue(jogoMock.jogo());
    }

    @Test
    void perdeNoPrimeiroCom2() {
        when(jogadorMock.lancar(any(), any())).thenReturn(2);
        Jogo jogoMock = new Jogo(jogadorMock, dadinho1Mock, dadinho2Mock);
        assertFalse(jogoMock.jogo());
    }

    @Test
    void ganhaNoSegundoTurno() {
        when(jogadorMock.lancar(any(), any()))
            .thenReturn(5)    // Primeira jogada
            .thenReturn(3)
            .thenReturn(2)
            .thenReturn(5);    // Ganha com o mesmo número
        
        Jogo jogoMock = new Jogo(jogadorMock, dadinho1Mock, dadinho2Mock);
        assertTrue(jogoMock.jogo());
    }

    @Test
    void perdeNoSegundoCom7() {
        when(jogadorMock.lancar(any(), any()))
            .thenReturn(5)    // Pontua
            .thenReturn(3)
            .thenReturn(2)
            .thenReturn(7);    // Perde com o 7
        
        Jogo jogoMock = new Jogo(jogadorMock, dadinho1Mock, dadinho2Mock);
        assertFalse(jogoMock.jogo());
    }
}
