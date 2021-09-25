using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    static private int puntos = 0;
    static private int vidas = 3;
    [SerializeField] int nivel = 1;
    [SerializeField] int nivelMax = 2;
    [SerializeField] int ladrillos = 3;
    [SerializeField] UnityEngine.UI.Text textoPuntos;
    [SerializeField] GameObject pelota;

    void Start()
    {
        ActualizarMarcador();
    }

    public void IncrementarPuntos()
    {
        puntos += 10;
        ladrillos--;
        ActualizarMarcador();

        if (ladrillos <= 0)
        {
            if (nivel < nivelMax)
            {
                nivel++;
                SceneManager.LoadScene("Nivel"
                    + nivel.ToString("00"));
            }
            else
            {
                puntos = 0;
                vidas = 3;
                nivel = 1;
                SceneManager.LoadScene("Menu");
            }
        }
    }

    public void PerderVida()
    {
        vidas--;
        ActualizarMarcador();
        pelota.SendMessage("Recolocar");
        if (vidas <= 0)
        {
            puntos = 0;
            vidas = 3;
            nivel = 1;
            SceneManager.LoadScene("Menu");
        }
    }

    private void ActualizarMarcador()
    {
        textoPuntos.text = "Puntos\n" + puntos
             + "\n\nVidas\n" + vidas
             + "\n\nNivel\n" + nivel
             + "\n\nRestantes\n" + ladrillos;
    }
}
