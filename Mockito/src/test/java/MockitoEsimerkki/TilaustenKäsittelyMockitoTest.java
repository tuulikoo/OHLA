package MockitoEsimerkki;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TilaustenKäsittelyMockitoTest {

   @Mock
   IHinnoittelija hinnoittelijaMock;

   @BeforeEach
   public void setup() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void testaaKäsittelyHintaAlle100() {
      // Arrange
      float alkuSaldo = 120.0f;
      float listaHinta = 35.0f;
      float alennus = 20.0f;
      float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

      Asiakas asiakas = new Asiakas(alkuSaldo);
      Tuote tuote = new Tuote("TDD in Action", listaHinta);

      when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);

      TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
      käsittelijä.setHinnoittelija(hinnoittelijaMock);
      käsittelijä.käsittele(new Tilaus(asiakas, tuote));

      assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);

      System.out.println("Expected saldo: " + loppuSaldo);
      System.out.println("Actual saldo: " + asiakas.getSaldo());
   }

   @Test
   public void testaaKäsittelyHintaYli100(){

      float alkuSaldo = 200.0f;
      float listaHinta = 139.0f;
      float alennus = 20.0f;
      float loppuSaldo = alkuSaldo - (listaHinta * (1 - (alennus+5) / 100));

      Asiakas asiakas = new Asiakas(alkuSaldo);
      Tuote tuote = new Tuote("TDD in Action", listaHinta);

      when (hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus+5);

      TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
      käsittelijä.setHinnoittelija(hinnoittelijaMock);
      käsittelijä.käsittele(new Tilaus(asiakas, tuote));

      assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);

      // Print expected and actual values
      System.out.println("Expected saldo over 100: " + loppuSaldo);
      System.out.println("Actual saldo over 100: " + asiakas.getSaldo());
   }

   @Test
   public void testaaKäsittelyHinta0(){
      float alkuSaldo = 200.0f;
      float listaHinta = 0.0f;
      float alennus = 15.0f;
      float loppuSaldo = alkuSaldo - (listaHinta * (1 - alennus / 100));

      Asiakas asiakas = new Asiakas(alkuSaldo);
      Tuote tuote = new Tuote("TDD in Action", listaHinta);

      when(hinnoittelijaMock.getAlennusProsentti(asiakas, tuote)).thenReturn(alennus);

      TilaustenKäsittely käsittelijä = new TilaustenKäsittely();
      käsittelijä.setHinnoittelija(hinnoittelijaMock);
      käsittelijä.käsittele(new Tilaus(asiakas, tuote));

      assertEquals(loppuSaldo, asiakas.getSaldo(), 0.001);


   }
}
