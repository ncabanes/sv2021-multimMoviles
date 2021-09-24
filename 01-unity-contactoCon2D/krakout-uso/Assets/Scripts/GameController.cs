using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GameController : MonoBehaviour
{
    private int puntos = 0;
    [SerializeField] UnityEngine.UI.Text textoPuntos;

    public void IncrementarPuntos()
    {
        puntos += 10;
        textoPuntos.text = "Puntos\n" + puntos;
    }
}
