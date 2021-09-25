using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PerderVida : MonoBehaviour
{
    [SerializeField] GameObject gameController;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerExit2D(Collider2D otro)
    {
        if (otro.tag == "Pelota")
        {
            gameController.SendMessage("PerderVida");
        }
    }
}
