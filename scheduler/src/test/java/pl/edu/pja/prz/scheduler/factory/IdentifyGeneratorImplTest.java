package pl.edu.pja.prz.scheduler.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.scheduler.factory.IdentifyGenerator;
import pl.edu.pja.prz.scheduler.factory.IdentifyGeneratorImpl;


class IdentifyGeneratorImplTest {

  private IdentifyGenerator identifyGenerator;

  @BeforeEach
  public void setUp() {
    identifyGenerator = new IdentifyGeneratorImpl();
  }

  @Test
  public void Should_GenerateId() {
    //Given

    //When
    String result = identifyGenerator.generateId();

    //Then
    assertNotNull(result);
  }
}
