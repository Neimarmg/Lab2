/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import M.Menu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neimarmoises
 */
public class MenuTest{
    
    public MenuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of selecionaMenu method, of class MenuControle.
     */
    @Test
    public void testMenu() {
       Menu m = new Menu(1, 2, "Pacientes");               
       assertEquals(1, m.getCod());
       assertEquals(2,  m.getCodTipoMenu());
       assertEquals("Pacientes",  m.getDescMenu());
    }

    
}
