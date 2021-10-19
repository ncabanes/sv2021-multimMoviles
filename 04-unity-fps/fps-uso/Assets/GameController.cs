using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.InputSystem;

public class GameController : MonoBehaviour
{
    [SerializeField] Camera camara;

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetButtonDown("Fire1"))
        {
            Debug.Log("Disparando...");
            float distanciaMaxima = 100;
            RaycastHit impacto;
            bool impactado = Physics.Raycast(camara.transform.position,
            camara.transform.forward, out impacto, distanciaMaxima);

            if (impactado)
            {
                Debug.Log("Disparo impactado");
                if (impacto.collider.CompareTag("Enemigo"))
                    Debug.Log("Enemigo acertado");
            }
        }
    }
}
