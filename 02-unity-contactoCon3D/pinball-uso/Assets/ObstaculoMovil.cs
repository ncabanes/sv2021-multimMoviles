using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObstaculoMovil : MonoBehaviour
{
    [SerializeField] Transform[] wayPoints;
    int numeroSiguientePos;
    float velocidad = 25f;
    float distanciaCambio = 1f;


    // Start is called before the first frame update
    void Start()
    {
        numeroSiguientePos = 0;
    }

    // Update is called once per frame
    void Update()
    {
        transform.position = Vector3.MoveTowards(
            transform.position,
            wayPoints[numeroSiguientePos].position,
            velocidad * Time.deltaTime);

        if (Vector3.Distance(transform.position,
            wayPoints[numeroSiguientePos].position) 
            < distanciaCambio)
        {
            numeroSiguientePos = 
                (numeroSiguientePos + 1) % wayPoints.Length;
        }

    }
}
