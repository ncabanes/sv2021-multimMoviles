using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    float velocidad = 10f;
    float fuerzaSalto = 5f;
    float alturaPersonaje;

    // Start is called before the first frame update
    void Start()
    {
        alturaPersonaje = GetComponent<Collider2D>().
            bounds.size.y;
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
            if (GetComponent<Collider2D>().
                IsTouchingLayers(
                    LayerMask.GetMask("CapaFondo")))
            {
                GetComponent<Rigidbody2D>().
                        AddForce(Vector2.up * fuerzaSalto,
                        ForceMode2D.Impulse);
            }

            /*
            RaycastHit2D hit = Physics2D.Raycast(
                transform.position, Vector2.down);
            if (hit.collider != null)
            {
                float distanciaAlSuelo = hit.distance;
                bool tocandoSuelo = distanciaAlSuelo < alturaPersonaje;
                if (tocandoSuelo)
                {
                    GetComponent<Rigidbody2D>().
                        AddForce(Vector2.up * fuerzaSalto,
                        ForceMode2D.Impulse);
                }

            }
            */
        }
    }
}
