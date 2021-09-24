using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Ladrillo : MonoBehaviour
{
    [SerializeField] Transform prefabExplosion;
    [SerializeField] GameObject gameController;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log("Boom");
    }

    private void OnCollisionEnter2D(
        Collision2D collision)
    {
        if (collision.gameObject.tag == "Pelota")
        {
            gameController.SendMessage("IncrementarPuntos");
            Transform explosion = Instantiate(
                prefabExplosion,
                transform.position,
                Quaternion.identity);
            Destroy(explosion.gameObject, 1f);
            Destroy(gameObject);
        }

    }
}
