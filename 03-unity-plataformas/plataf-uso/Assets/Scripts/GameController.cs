using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    UnityEngine.UI.Text marcador;
    static int puntos = 0;
    static int vidas = 3;
    static int nivelActual = 1;
    int nivelMax = 2;
    int itemsRestantes;

    void Start()
    {
        itemsRestantes = FindObjectsOfType<Item>().Length;
        marcador = FindObjectOfType<UnityEngine.UI.Text>();
        ActualizarMarcador();
    }

    // Update is called once per frame
    void Update()
    {

    }

    public void ItemRecogido()
    {
        puntos += 10;
        itemsRestantes--;
        ActualizarMarcador();
        if (itemsRestantes <= 0)
        {
            nivelActual++;
            if (nivelActual <= nivelMax)
                SceneManager.LoadScene("Nivel" + nivelActual.ToString("00"));
            else
                StartCoroutine(TerminarPartida());
        }
    }

    private void ActualizarMarcador()
    {
        marcador.text = "Puntos: " + puntos +
                    "\nVidas: " + vidas;
    }

    public void PerderVida()
    {
        vidas--;
        ActualizarMarcador();
        FindObjectOfType<Player>().SendMessage("Recolocar");
        if (vidas <= 0)
        {
            StartCoroutine(TerminarPartida());
        }
    }

    private IEnumerator TerminarPartida()
    {
        marcador.text = "Game over!!!";
        marcador.color = new Color(255, 0, 0);
        yield return new WaitForSeconds(3);
        puntos = 0;
        vidas = 3;
        nivelActual = 1;
        SceneManager.LoadScene("Bienvenida");
    }
}
