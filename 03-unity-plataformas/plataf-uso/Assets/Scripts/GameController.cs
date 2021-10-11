using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameController : MonoBehaviour
{
    [SerializeField] UnityEngine.UI.Text marcador;
    int puntos = 0;
    int vidas = 3;
    int itemsRestantes;

    // Start is called before the first frame update
    void Start()
    {
        itemsRestantes = FindObjectsOfType<Item>().Length;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void ItemRecogido()
    {
        puntos += 10;
        ActualizarMarcador();
        itemsRestantes--;
        if (itemsRestantes <= 0)
        {
            // TO DO: Avanzar nivel
            Debug.Log("Sin items restantes");
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
            StartCoroutine( TerminarPartida() );
        }
    }

    private IEnumerator TerminarPartida()
    {
        marcador.text = "Game over!!!";
        marcador.color = new Color(255, 0, 0);
        yield return new WaitForSeconds(3);
        SceneManager.LoadScene("Bienvenida");
    }
}
