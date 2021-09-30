using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PersonajeMovil : MonoBehaviour
{
    float velocidadAvance = 20f;
    float velocidadRotacion = 30f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float avance = Input.GetAxis("Vertical") *
            velocidadAvance * Time.deltaTime;
        float rotacion = Input.GetAxis("Horizontal") * 
            velocidadRotacion * Time.deltaTime;
        transform.Rotate(Vector3.up, rotacion);
        transform.position += transform.forward * 
            avance;
    }
}
