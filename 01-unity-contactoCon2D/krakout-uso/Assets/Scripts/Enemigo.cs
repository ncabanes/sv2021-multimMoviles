using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemigo : MonoBehaviour
{
    [SerializeField] float velocidadX = 1;
    [SerializeField] float velocidadY = 0.8f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(
            velocidadX * Time.deltaTime,
            velocidadY * Time.deltaTime,
            0);

        if ((transform.position.x > 4)
            || (transform.position.x <  -4))
        {
            velocidadX = -velocidadX;
        }

        if ((transform.position.y > 3)
            || (transform.position.y < -3))
        {
            velocidadY = -velocidadY;
        }
    }
}
