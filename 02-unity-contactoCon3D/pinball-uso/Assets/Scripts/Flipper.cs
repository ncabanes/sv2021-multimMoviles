using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Flipper : MonoBehaviour
{
    [SerializeField] string lado = "D";

    HingeJoint bisagra;
    JointSpring muelle;
    float fuerzaFlipper = 10000f;
    float amortigFlipper = 150f;

    // Start is called before the first frame update
    void Start()
    {
        bisagra = GetComponent<HingeJoint>();
        muelle = new JointSpring();
        muelle.damper = amortigFlipper;
        muelle.spring = fuerzaFlipper;
    }

    // Update is called once per frame
    void Update()
    {
        if ((lado == "I") 
            && (Input.GetAxis("Horizontal") < -0.1f))
        {
            bisagra.spring = muelle;
            muelle.targetPosition = -60;
        }
        else if ((lado == "D") 
            && (Input.GetAxis("Horizontal") > 0.1f))
        {
            bisagra.spring = muelle;
            muelle.targetPosition = 60;
        }
        else
        {
            bisagra.spring = muelle;
            muelle.targetPosition = 0;            
        }
    }
}
