using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LadrilloDosGolpes : MonoBehaviour
{
    [SerializeField] Transform prefabExplosion;
    [SerializeField] GameObject gameController;
    private int golpes;

    // Start is called before the first frame update
    void Start()
    {
        golpes = 0;
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "Pelota")
        {
            golpes++;

            if (golpes == 1)
            {
                GetComponent<SpriteRenderer>().color = new Color(127, 0, 0);
            }
            else if (golpes >= 2)
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
}
