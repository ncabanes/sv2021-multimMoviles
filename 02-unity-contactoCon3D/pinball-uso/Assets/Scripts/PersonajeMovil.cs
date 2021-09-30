using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PersonajeMovil : MonoBehaviour
{
    float velocidad = 10f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float vertical = Input.GetAxis("Vertical");
        float horizontal = Input.GetAxis("Horizontal");
        transform.Translate(
            -vertical * velocidad * Time.deltaTime,
            0,
            horizontal * velocidad * Time.deltaTime);
    }
}
