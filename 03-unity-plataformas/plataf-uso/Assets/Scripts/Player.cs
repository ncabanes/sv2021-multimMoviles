using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    float velocidad = 10f;
    float fuerzaSalto = 5f;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        float horizontal = Input.GetAxis("Horizontal");
        transform.Translate(
            horizontal * velocidad * Time.deltaTime,
            0,
            0
            );
        if (Input.GetButtonDown("Jump"))
        {
            GetComponent<Rigidbody2D>().
                AddForce(Vector2.up * fuerzaSalto, 
                ForceMode2D.Impulse);
        }
    }
}
