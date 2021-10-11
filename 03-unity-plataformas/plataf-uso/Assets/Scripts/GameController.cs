using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    [SerializeField] UnityEngine.UI.Text marcador;
    int puntos = 0;
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
        marcador.text = "Puntos: " + puntos +
            "\nVidas: 3";
        itemsRestantes--;
        if (itemsRestantes <= 0)
        {
            // TO DO: Avanzar nivel
            Debug.Log("Sin items restantes");
        }
            
    }
}
